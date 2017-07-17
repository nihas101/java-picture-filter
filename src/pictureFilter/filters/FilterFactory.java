package pictureFilter.filters;

import pictureFilter.filters.mirrorFilters.MirrorHorBottom;
import pictureFilter.filters.mirrorFilters.MirrorHorTop;
import pictureFilter.filters.mirrorFilters.MirrorVertLeft;
import pictureFilter.filters.mirrorFilters.MirrorVertRight;

import java.util.Collection;
import java.util.HashMap;

public class FilterFactory {

    private HashMap<String, Filter> filterHashMap;

    public FilterFactory(HashMap<String, Filter> filterHashMap){
        this.filterHashMap = filterHashMap;
        addAllFilters();
    }

    public Filter getFilter(String filterString){
        Filter filter = filterHashMap.getOrDefault(filterString.toLowerCase(), null);
        if(filter == null) throw new FilterNotFoundError(filterString);

        return filter;
    }

    private void addAllFilters(){
        Filter bwFilter = new Grayscale_Filter();
        Filter testFilter = new TestFilter();
        Filter mvlFilter = new MirrorVertLeft();
        Filter mvrFilter = new MirrorVertRight();
        Filter mhbFilter = new MirrorHorBottom();
        Filter mhtFilter = new MirrorHorTop();

        this.filterHashMap.put(bwFilter.getFilterName().toLowerCase(), bwFilter);
        this.filterHashMap.put(testFilter.getFilterName().toLowerCase(), testFilter);
        this.filterHashMap.put(mvlFilter.getFilterName().toLowerCase(), mvlFilter);
        this.filterHashMap.put(mvrFilter.getFilterName().toLowerCase(), mvrFilter);
        this.filterHashMap.put(mhbFilter.getFilterName().toLowerCase(), mhbFilter);
        this.filterHashMap.put(mhtFilter.getFilterName().toLowerCase(), mhtFilter);
    }

    public Collection<Filter> getFilters(){
        return this.filterHashMap.values();
    }
}