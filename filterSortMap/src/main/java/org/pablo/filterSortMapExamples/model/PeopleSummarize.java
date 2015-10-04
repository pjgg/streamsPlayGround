package org.pablo.filterSortMapExamples.model;

public class PeopleSummarize {
	
	private long ageSum;
	
	private long maleSum;
	
	private long femaleSum;
	
	public PeopleSummarize(){}
	
	public PeopleSummarize(Long ageSum, long maleSum, long femaleSum){
		this.ageSum = ageSum;
		this.maleSum = maleSum;
		this.femaleSum = femaleSum;
	}

	public long getAgeSum() {
		return ageSum;
	}

	public long getMaleSum() {
		return maleSum;
	}

	public long getFemaleSum() {
		return femaleSum;
	}

	public void setAgeSum(long ageSum) {
		this.ageSum = ageSum;
	}

	public void setMaleSum(long maleSum) {
		this.maleSum = maleSum;
	}

	public void setFemaleSum(long femaleSum) {
		this.femaleSum = femaleSum;
	}

}
