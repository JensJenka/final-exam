package no.exam.orderservice.extensions

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class WireMockExtension : BeforeAllCallback, AfterAllCallback {

    private val wm = WireMockServer(WireMockConfiguration().port(8081))

    override fun beforeAll(context: ExtensionContext?) {

        wm.start()

        wm.stubFor(
            get(urlEqualTo("/api/payment/1"))
                .willReturn(
                    ok(
                        jacksonObjectMapper().writeValueAsString(
                            mapOf("orderId" to "1", "payed" to "false")
                        )
                    )
                )
        )
    }

    override fun afterAll(context: ExtensionContext?) {
        wm.stop()
    }
}
