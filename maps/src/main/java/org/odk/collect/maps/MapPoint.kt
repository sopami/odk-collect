/*
 * Copyright (C) 2018 Nafundi
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.odk.collect.maps

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MapPoint @JvmOverloads constructor(
    @JvmField val latitude: Double,
    @JvmField val longitude: Double,
    @JvmField val altitude: Double = 0.0,
    @JvmField val accuracy: Double = 0.0
) : Parcelable
