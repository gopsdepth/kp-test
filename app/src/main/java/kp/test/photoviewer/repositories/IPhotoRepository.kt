package kp.test.photoviewer.repositories

import kp.test.photoviewer.models.PhotoInfoModel

interface IPhotoRepository {
    fun getPhotoList(page: Int, pageSize: Int): List<PhotoInfoModel>
}