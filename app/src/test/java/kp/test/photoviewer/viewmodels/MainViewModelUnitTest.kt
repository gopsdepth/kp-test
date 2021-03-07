package kp.test.photoviewer.viewmodels

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kp.test.photoviewer.models.PhotoInfoModel
import kp.test.photoviewer.repositories.IPhotoRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelUnitTest {
    @Mock
    private lateinit var repositoryMock: IPhotoRepository
    private val pageSizeMock = 3

    @Test
    fun repositoryIsEmpty_showTextNoImage() {
        // Arrange
        assertNotNull(repositoryMock)

        val expectedIsShowList = false
        `when`(
            repositoryMock.getPhotoList(
                Mockito.anyInt(),
                Mockito.anyInt()
            )
        ).thenReturn(emptyList())

        // Action
        val viewModel = MainViewModel(repositoryMock, pageSizeMock)

        // Assert
        assertEquals(expectedIsShowList, viewModel.isShowList)
    }

    @Test
    fun repositoryIsNotEmpty_showPhotoListView() {
        // Arrange
        assertNotNull(repositoryMock)

        val mockPhotoList = listOf(
            PhotoInfoModel("", "")
        )
        val expectedIsShowList = true
        `when`(repositoryMock.getPhotoList(Mockito.anyInt(), Mockito.anyInt())).thenReturn(
            mockPhotoList
        )

        // Action
        val viewModel = MainViewModel(repositoryMock, pageSizeMock)

        // Assert
        assertEquals(expectedIsShowList, viewModel.isShowList)
    }

    @Test
    fun repositoryGetFirstPage_showItemsCountEqualPageSize() {
        // Arrange
        assertNotNull(repositoryMock)

        val mockPhotoList = listOf(
            PhotoInfoModel("", ""),
            PhotoInfoModel("", ""),
            PhotoInfoModel("", ""),
        )
        val expectedItemCount = pageSizeMock
        `when`(repositoryMock.getPhotoList(Mockito.anyInt(), Mockito.anyInt())).thenReturn(
            mockPhotoList
        )

        // Action
        val viewModel = MainViewModel(repositoryMock, pageSizeMock)

        // Assert
        assertEquals(expectedItemCount, viewModel.photoItemList.size)
    }

    @Test
    fun repositoryHasSecondPageAndCallNextPage_showItemsCountEqualPageSizeTwoTimes() {
        // Arrange
        assertNotNull(repositoryMock)

        val mockPhotoList = listOf(
            PhotoInfoModel("", ""),
            PhotoInfoModel("", ""),
            PhotoInfoModel("", ""),
        )
        val expectedItemCount = pageSizeMock * 2
        `when`(repositoryMock.getPhotoList(Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockPhotoList)

        // Action
        val viewModel = MainViewModel(repositoryMock, pageSizeMock)
        viewModel.nextPage()

        // Assert
        assertEquals(expectedItemCount, viewModel.photoItemList.size)
    }

    @Test
    fun repositoryHasSecondPageAndCallRefresh_showItemsCountEqualPageSize() {
        // Arrange
        assertNotNull(repositoryMock)

        val mockPhotoList = listOf(
            PhotoInfoModel("", ""),
            PhotoInfoModel("", ""),
            PhotoInfoModel("", ""),
        )
        val expectedItemCount = pageSizeMock
        `when`(repositoryMock.getPhotoList(Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockPhotoList)

        // Action
        val viewModel = MainViewModel(repositoryMock, pageSizeMock)
        viewModel.refresh()

        // Assert
        assertEquals(expectedItemCount, viewModel.photoItemList.size)
    }
}