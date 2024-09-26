import React, { useState } from 'react'
import axios from "axios"

const RoomImageForm = ({ onSuccess }) => {
    const [image, setImage] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("image", image);

        try {
            await axios.post("http://localhost:8080/room-images/create", formData, {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            });
            setImage(null);
            onSuccess(); // Callback to refresh the list after creating
        } catch (error) {
            console.error("Error creating hotel image:", error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="file" onChange={(e) => setImage(e.target.files[0])} required />
            <button type="submit">Upload Image</button>
        </form>
    );
};

export default RoomImageForm