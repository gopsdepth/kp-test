package kp.test.photoviewer.viewmodels

import kp.test.photoviewer.models.PhotoInfoModel
import kp.test.photoviewer.repositories.IPhotoRepository

class MainViewModel(private val repository: IPhotoRepository, private val _pageSize: Int) {
    private var _currentPage = 1;
    private var _photoList: List<PhotoInfoModel> = repository.getPhotoList(_currentPage, _pageSize)

    var isShowList: Boolean = _photoList.isNotEmpty()
    var photoItemList: List<PhotoCardViewModel> = emptyList()
        get() = _photoList.map { PhotoCardViewModel(it) }

    fun nextPage() {
        _photoList = _photoList + repository.getPhotoList(++_currentPage, _pageSize)
    }

    fun refresh() {
        _currentPage = 1
        _photoList = repository.getPhotoList(++_currentPage, _pageSize)
    }
}