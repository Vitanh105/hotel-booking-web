import React from 'react'
import RoomImageForm from './RoomImageForm';
import RoomImageList from './RoomImageList';

const RoomImageManager = () => {
    const refreshRoomImages = () => {
        window.location.reload();
    }
    return (
        <div>
            <RoomImageForm onSuccess={refreshRoomImages} />
            <RoomImageList />
        </div>
    )
}

export default RoomImageManager