
package dev.patrickdorn.realestatefinder.core.di

import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class DiSanityTest {
    @Test
    fun checkAllModules() {
        appModule.verify()
        dataModule.verify()
    }
}
