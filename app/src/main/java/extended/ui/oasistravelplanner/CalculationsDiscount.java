package extended.ui.oasistravelplanner;

public class CalculationsDiscount {

    protected float calcfamilydiscount(Float value) {
        Float ans = (value * 10/100) ;
        return ans;
    }
    protected float calcholidaydiscount(Float value) {
        Float ans = (value * 5/100) ;
        return ans;
    }
}
