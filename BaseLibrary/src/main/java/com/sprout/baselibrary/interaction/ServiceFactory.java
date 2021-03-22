package com.sprout.baselibrary.interaction;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    public static ServiceFactory getInstance() {
        return instance;
    }

    // 为组件化接口提供get及set方法
    private IHomeService homeService;
    private ISearchService searchService;

    public IHomeService getHomeService() {
        // 避免在移除组件后报错。这里不返回null，返回一个空实现类
        if (homeService == null) {
            homeService = new EmptyHomeService();
        }
        return homeService;
    }

    public void setHomeService(IHomeService homeService) {
        this.homeService = homeService;
    }

    public ISearchService getSearchService() {
        if (searchService == null) {
            searchService = new EmptySearchService();
        }
        return searchService;
    }

    public void setMineService(ISearchService searchService) {
        this.searchService = searchService;
    }
}