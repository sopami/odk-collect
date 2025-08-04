package org.odk.collect.shared.collections

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.json.JSONArray
import org.junit.Test
import org.odk.collect.shared.collections.CollectionExtensions.has
import org.odk.collect.shared.collections.CollectionExtensions.itemFromHashOf

class CollectionExtensionsTest {

    @Test
    fun `itemFromHashOf returns item using hashcode as index`() {
        assertThat(listOf("0", "1").itemFromHashOf(HashCode(0)), equalTo("0"))
        assertThat(listOf("0", "1").itemFromHashOf(HashCode(1)), equalTo("1"))
    }

    @Test
    fun `itemFromHashOf wraps around when hashcode is greater than index`() {
        assertThat(listOf("0", "1").itemFromHashOf(HashCode(2)), equalTo("0"))
        assertThat(listOf("0", "1").itemFromHashOf(HashCode(5)), equalTo("1"))
    }

    @Test
    fun `itemFromHashOf works with negative hashcode`() {
        assertThat(listOf("0", "1").itemFromHashOf(HashCode(-1)), equalTo("1"))
    }

    @Test
    fun `has returns true if given value exists in json array`() {
        assertThat(JSONArray(listOf("blah")).has("blah"), equalTo(true))
    }

    @Test
    fun `has returns false if given value does not exist in json array`() {
        assertThat(JSONArray(listOf("blah")).has("blah2"), equalTo(false))
    }

    @Test
    fun `has returns false if json array is empty`() {
        assertThat(JSONArray().has("blah"), equalTo(false))
    }
}

private data class HashCode(private val value: Int) {
    override fun hashCode(): Int {
        return value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HashCode

        if (value != other.value) return false

        return true
    }
}
