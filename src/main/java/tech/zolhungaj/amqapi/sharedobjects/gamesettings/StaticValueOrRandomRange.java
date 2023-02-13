package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import com.squareup.moshi.Json;
import lombok.Data;

import java.util.List;

@Data
public abstract class StaticValueOrRandomRange{
    @Json(name = "standardValue")
    private final int value;

    @Json(name = "randomValue")
    private final List<Integer> randomRange;

    @Json(name = "randomOn")
    private final boolean randomOn;

    protected StaticValueOrRandomRange(int value, List<Integer> randomRange, boolean randomOn){
        this.value = value;
        this.randomRange = List.copyOf(randomRange);
        this.randomOn = randomOn;
    }
    protected StaticValueOrRandomRange(Range range){
        this(range.start(), range.end());
    }

    protected StaticValueOrRandomRange(int start, int end){
        if(start == end){
            if(isValid(start)){
                this.value = start;
                this.randomRange = defaultRandomRange();
                this.randomOn = false;
            }else{
                throw new IllegalArgumentException("Value is outside range");
            }
        }else if(isValidRange(start, end)){
            this.randomOn = true;
            this.randomRange = List.of(start, end);
            this.value = defaultStandardValue();
        }else{
            throw new IllegalArgumentException("Value is outside range");
        }
    }

    protected StaticValueOrRandomRange(int value){
        if(isValid(value)){
            this.value = value;
            this.randomRange = defaultRandomRange();
            this.randomOn = false;
        }else{
            throw new IllegalArgumentException("Value is outside range");
        }
    }

    protected abstract int defaultStandardValue();
    protected abstract List<Integer> defaultRandomRange();
    private boolean isValidRange(int start, int end){
        return start <= end && isValid(start) && isValid(end);
    }
    private boolean isValid(int value) {
        return min() <= value && value <= max();
    }

    protected abstract int min();
    protected abstract int max();

    public final Range from(){
        if(randomOn){
            return new Range(randomRange.get(0), randomRange.get(1));
        }else{
            return new Range(value, value);
        }
    }
}
