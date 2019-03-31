package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.SearchModel;
import com.bwei.weidustore.utils.Contract;

/**
 * @Auther: 李
 * @Date: 2019/3/29 19:32:00
 * @Description:
 */
public class SearchPresenter extends BasePresenter<SearchModel> implements Contract.ISearchPresenter {
    Contract.ISearchView iSearchView;
    private final SearchModel searchModel;

    public SearchPresenter(Contract.ISearchView iSearchView) {
        this.iSearchView = iSearchView;
        searchModel = new SearchModel();
    }

    @Override
    public void getSearchData(int page, String keyword) {
        searchModel.getSearchData(page, keyword, new Contract.ISearchModel.ISearchDataCallBack() {
            @Override
            public void onSuccess(Object o) {
                iSearchView.getSearchData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
