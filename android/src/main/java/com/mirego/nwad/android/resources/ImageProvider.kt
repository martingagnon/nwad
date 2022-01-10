package com.mirego.nwad.android.resources

import android.content.Context
import com.mirego.nwad.android.R
import com.mirego.nwad.resources.ImageResource
import com.mirego.trikot.viewmodels.declarative.configuration.VMDImageProvider
import com.mirego.trikot.viewmodels.declarative.properties.VMDImageResource
import com.mirego.trikot.viewmodels.declarative.properties.VMDNoImageResource

class ImageProvider : VMDImageProvider {
    override fun resourceIdForResource(resource: VMDImageResource, context: Context): Int? {
        return when (resource) {
            is VMDNoImageResource -> null
            is ImageResource -> mapSampleImageResource(resource)
            else -> null
        }
    }

    private fun mapSampleImageResource(resource: ImageResource): Int? {
        return when (resource) {
            ImageResource.ICON_CLOSE -> R.drawable.ic_close
            else -> null
        }
    }
}
