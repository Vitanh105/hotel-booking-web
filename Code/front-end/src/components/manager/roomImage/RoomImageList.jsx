import React, { useEffect, useState } from "react";
import axios from "axios";

const RoomImageList = () => {
    const [roomImages, setRoomImages] = useState([]);
    const [editImage, setEditImage] = useState(null);
    const [editId, setEditId] = useState(null);

    useEffect(() => {
        fetchRoomImages();
    }, []);

    const fetchRoomImages = () => {
        axios
            .get("http://localhost:8080/room-images/all")
            .then((response) => setRoomImages(response.data))
            .catch((error) => console.error("Error fetching room images:", error));
    };

    const handleEdit = (id) => {
        setEditId(id);
    };

    const handleImageChange = (e) => {
        setEditImage(e.target.files[0]);
    };

    const handleUpdate = async (id) => {
        try {
            const formData = new FormData();
            formData.append("image", editImage);

            await axios.put(`http://localhost:8080/room-images/update/${id}`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            });
            setEditId(null);
            fetchRoomImages();
        } catch (error) {
            console.error("Error updating room image:", error);
        }
    };

    return (
        <div>
            <h1>Room Images</h1>
            <ul>
                {roomImages.map((roomImage) => (
                    <li key={roomImage.id}>
                        <img src={`data:image/jpeg;base64,${roomImage.image}`} alt="room" width={200} />
                        {editId === roomImage.id ? (
                            <input type="file" onChange={handleImageChange} />
                        ) : (
                            <button onClick={() => handleEdit(roomImage.id)}>Edit</button>
                        )}
                        {editId === roomImage.id && (
                            <button onClick={() => handleUpdate(roomImage.id)}>Update</button>
                        )}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default RoomImageList