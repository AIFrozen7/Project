package com.bwei.weidustore.bean;

import java.util.List;

/**
 * @Auther: 李
 * @Date: 2019/2/28 09:27:01
 * @Description:
 */
public class AllBean {
    private List<BannerBean.ResultBean> result;
    private ShopListBean.ResultBean.RxxpBean rxxp;
    private ShopListBean.ResultBean.PzshBean pzsh;
    private ShopListBean.ResultBean.MlssBean mlss;
    private AllBean(Builder builder){
        this.result = builder.result;
        this.mlss = builder.mlss;
        this.pzsh = builder.pzsh;
        this.rxxp = builder.rxxp;
    }

    public List<BannerBean.ResultBean> getResult() {
        return result;
    }

    public void setResult(List<BannerBean.ResultBean> result) {
        this.result = result;
    }

    public ShopListBean.ResultBean.RxxpBean getRxxp() {
        return rxxp;
    }

    public void setRxxp(ShopListBean.ResultBean.RxxpBean rxxp) {
        this.rxxp = rxxp;
    }

    public ShopListBean.ResultBean.PzshBean getPzsh() {
        return pzsh;
    }

    public void setPzsh(ShopListBean.ResultBean.PzshBean pzsh) {
        this.pzsh = pzsh;
    }

    public ShopListBean.ResultBean.MlssBean getMlss() {
        return mlss;
    }

    public void setMlss(ShopListBean.ResultBean.MlssBean mlss) {
        this.mlss = mlss;
    }


    //静态内部类Builder
    public static class Builder{
        private List<BannerBean.ResultBean> result;
        private ShopListBean.ResultBean.RxxpBean rxxp;
        private ShopListBean.ResultBean.PzshBean pzsh;
        private ShopListBean.ResultBean.MlssBean mlss;

        public Builder setResult(List<BannerBean.ResultBean> result) {
            this.result = result;
            return this;
        }

        public Builder setRxxp(ShopListBean.ResultBean.RxxpBean rxxp) {
            this.rxxp = rxxp;
            return this;
        }

        public Builder setPzsh(ShopListBean.ResultBean.PzshBean pzsh) {
            this.pzsh = pzsh;
            return this;
        }

        public Builder setMlss(ShopListBean.ResultBean.MlssBean mlss) {
            this.mlss = mlss;
            return this;
        }
        public AllBean build(){
            return new AllBean(this);
        }
    }

}
