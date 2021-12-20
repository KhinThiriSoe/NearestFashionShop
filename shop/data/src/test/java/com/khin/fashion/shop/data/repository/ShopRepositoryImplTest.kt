package com.khin.fashion.shop.data.repository

import com.khin.fashion.core.domain.entity.RepositoryResult
import com.khin.fashion.shop.data.remote.*
import com.khin.fashion.shop.domain.LocalPickup
import com.khin.fashion.shop.domain.repository.ShopRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class ShopRepositoryImplTest {

    private val shopService: ShopService = mock()

    private val SUT: ShopRepository = ShopRepositoryImpl(shopService)

    @Test
    fun `success - getShop - pickup list returned`() = runBlockingTest {
        whenever(shopService.getShopList()).thenReturn(createRemoteShop())

        val result = SUT.getShops()

        assertEquals(RepositoryResult.Success(listOf<LocalPickup>(createLocalPickup())), result)
    }

    private fun createRemoteShop() = RemoteShop(
        number_of_new_locations = 5,
        listOf(
            Pickup(
                feature = "fast_delivery",
                id_pickup_location = 37,
                id_country = 206,
                id_state = 315,
                id_carrier = 35,
                company = Any(),
                nps_link = "https://pomelo3.typeform.com/to/WIEe6G",
                alias = "All Seasons",
                address1 = "Unit 205 Level 2 CRC building, All Seasons Place87 Wireless Road,",
                address2 = " Khwaeng Lumphini, Khet Pathum Wan",
                district = "Pathum Wan ",
                city = "Bangkok",
                postcode = "10330",
                latitude = 13.739272,
                longitude = 100.548268,
                phone = "02-054-3201",
                nearest_bts = "Phloen Chit",
                notable_area = Any(),
                hours1 = "Mon - Sat: 10:00 AM - 08:00 PM",
                hours2 = "",
                hours3 = "",
                description = "",
                is_featured = false,
                subtype = "store",
                store_image_path = "{\"primary\":{\"landscape\":\"img/pickup-locations/main-image-landscape/allseasonplace.jpg\",\"full_landscape\":\"https://pomelofashion-staging.imgix.net/img/pickup-locations/main-image-landscape/allseasonplace.jpg\",\"portrait\":\"img/retail/stores/default/primary/default_portrait.jpg\",\"full_portrait\":\"https://pomelofashion-staging.imgix.net/img/retail/stores/default/primary/default_portrait.jpg\"},\"secondary\":\"img/pickup-locations/secondary-image/allseasonplace.jpg\",\"full_secondary\":\"https://pomelofashion-staging.imgix.net/img/pickup-locations/secondary-image/allseasonplace.jpg\"}",
                floormap_image_path = "{\"main\":\"img/pickup-locations/floor-map/allseasonplace.jpg\",\"full_main\":\"https://pomelofashion-staging.imgix.net/img/pickup-locations/floor-map/allseasonplace.jpg\",\"zoomed\":\"img/pickup-locations/floor-map-zoomed/allseasonplace.jpg\",\"full_zoomed\":\"https://pomelofashion-staging.imgix.net/img/pickup-locations/floor-map-zoomed/allseasonplace.jpg\"}",
                active = true,
                floor_number = "2",
                status = "active",
                id_zone = 3,
                features = listOf(),
                hours = listOf(),
                id_partner_store = 1,
                images = Images(
                    store = Store(
                        primary = Primary(
                            landscape = "img/partner/partner_images/20200511162555_5eb91a232429e_image.jpg",
                            full_landscape = "https://pomelofashion-staging.imgix.net/img/partner/partner_images/20200511162555_5eb91a232429e_image.jpg",
                            portrait = "img/partner/partner_images/20200511162555_5eb91a232429e_image.jpg",
                            full_portrait = "https://pomelofashion-staging.imgix.net/img/partner/partner_images/20200511162555_5eb91a232429e_image.jpg"
                        ),
                        full_secondary = "",
                        secondary = ""
                    ), floormap = Floormap(
                        full_main = "",
                        full_zoomed = "",
                        main = "img/pickup-locations/default/partner.jpg",
                        zoomed = "img/pickup-locations/default/partner.jpg"
                    )
                ),
                is_default_location = true,
                is_new_location = true,
                payment_methods = listOf(),
                type = ""
            )
        )
    )

    private fun createLocalPickup() = LocalPickup(
        pick_active = true,
        pick_alias = "All Seasons",
        pick_address = "Unit 205 Level 2 CRC building, All Seasons Place87 Wireless Road,",
        pick_city = "Bangkok",
        pick_latitude = 13.739272,
        pick_longitude = 100.548268,
        distance = 0f
    )
    // endregion helpers
}