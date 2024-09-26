import React from 'react'
import RoomImageUpload from './RoomImageUpload'
import RoomImageDisplay from './RoomImageDisplay'

const ManagerLayout = () => {
    return (
        <div>
            <RoomImageUpload />
            <RoomImageDisplay />
        </div>
    )
}

export default ManagerLayout