package org.pablo.filterSortMapExamples;


public enum ServiceLocator {

	INSTANCE;
	
	private final FilterSortMapService filterSortMapService;
	
	private ServiceLocator(){
		filterSortMapService = new FilterSortMapService();
	}
	
	public FilterSortMapService getFilterSortMapService(){
		return this.filterSortMapService;
	}
	
}
