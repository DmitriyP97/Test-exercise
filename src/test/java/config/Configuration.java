package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:application-${env}.properties",
        "classpath:selenide.properties",
        "classpath:general.properties"
})
public interface Configuration extends Config {

    @Config.Key("selenide.baseUrl")
    String baseUrl();
    @Key("env")
    String environment();
    @DefaultValue("local")
    @Key("selenide.remote")
    String targetExecution();
    @Key("selenide.timeout")
    Long globalTimeout();
    @Key("selenide.browser")
    String browser();

    @Key("minimalPage.url")
    String minimalPageUrl();
    @Key("ks.tg")
    int targetGroup();
    @Key("testInstallId")
    String testInstallId();
    @Key("productWithCrossellItems.url")
    String productWithCrossellItems();
    @Key("product.url")
    String productUrl();
    @Key("unitProduct.url")
    String unitProductUrl();
    @Key("listing.url")
    String listingUrl();
    @Key("listingPetsGoods.url")
    String listingPetsGoodsUrl();
    @Key("listingFood.url")
    String listingFoodUrl();
    @Key("cart.url")
    String cartUrl();
    @Key("productWithoutPromo.url")
    String productWithoutPromoUrl();
    @Key("merchantsList.url")
    String merchantsListUrl();
    @Key("merchantPage.url")
    String merchantPageUrl();
    @Key("magnumMerchantPage.url")
    String magnumMerchantPageUrl();
    @Key("weightProduct.url")
    String weightProductUrl();
    @Key("weightProduct2.url")
    String weightProductUrl2();
    @Key("pieceByWeightProduct.url")
    String pieceByWeightProductUrl();
    @Key("pieceByWeightProduct2.url")
    String pieceByWeightProduct2Url();
    @Key("catalog.url")
    String catalogUrl();
    @Key("cheapProduct.url")
    String cheapProductUrl();
    @Key("strongAlcohol.url")
    String strongAlcoholUrl();
    @Key("tenPercentPromoProduct.url")
    String tenPercentPromoUrl();
    @Key("zhumaProduct.url")
    String zhumaProductUrl();
    @Key("listingZhuma.url")
    String listingZhumaUrl();
    @Key("prescriptionProduct.url")
    String prescriptionProductUrl();
    @Key("productDesktop.url")
    String productDesktopUrl();
    @Key("leroyMerlinLanding.url")
    String leroyMerlinLandingUrl();
    @Key("cardioMedsListing.url")
    String cardioMedsListingUrl();
    @Key("shoes.url")
    String shoesProductUrl();
    @Key("topClothes.url")
    String topClothesUrl();
    @Key("bottomClothes.url")
    String bottomClothesUrl();
}