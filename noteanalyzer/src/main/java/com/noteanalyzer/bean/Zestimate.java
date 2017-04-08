package com.noteanalyzer.bean;

public class Zestimate
{
    private Amount amount;

    private String percentile;

    private OneWeekChange oneWeekChange;

    private String lastupdated;

    private ValuationRange valuationRange;

    private ValueChange valueChange;

    public Amount getAmount ()
    {
        return amount;
    }

    public void setAmount (Amount amount)
    {
        this.amount = amount;
    }

    public String getPercentile ()
    {
        return percentile;
    }

    public void setPercentile (String percentile)
    {
        this.percentile = percentile;
    }

    public OneWeekChange getOneWeekChange ()
    {
        return oneWeekChange;
    }

    public void setOneWeekChange (OneWeekChange oneWeekChange)
    {
        this.oneWeekChange = oneWeekChange;
    }

    public String getLastupdated ()
    {
        return lastupdated;
    }

    public void setLastupdated (String lastupdated)
    {
        this.lastupdated = lastupdated;
    }

    public ValuationRange getValuationRange ()
    {
        return valuationRange;
    }

    public void setValuationRange (ValuationRange valuationRange)
    {
        this.valuationRange = valuationRange;
    }

    public ValueChange getValueChange ()
    {
        return valueChange;
    }

    public void setValueChange (ValueChange valueChange)
    {
        this.valueChange = valueChange;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [amount = "+amount+", percentile = "+percentile+", oneWeekChange = "+oneWeekChange+", last-updated = "+lastupdated+", valuationRange = "+valuationRange+", valueChange = "+valueChange+"]";
    }
}
