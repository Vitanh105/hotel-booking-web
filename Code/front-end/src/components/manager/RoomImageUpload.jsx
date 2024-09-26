import React, { useState } from "react";
import axios from "axios";

const RoomImageUpload = ({ roomId }) => {
  const [image, setImage] = useState(null);

  const handleFileChange = (e) => {
    setImage(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("image", image);
    formData.append("roomId", roomId);

    try {
      const response = await axios.post("http://localhost:8080/api/room-images/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      console.log("Image uploaded successfully:", response.data);
    } catch (error) {
      console.error("Error uploading image:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="file" onChange={handleFileChange} accept="image/*" />
      <button type="submit">Upload Image</button>
    </form>
  );
};

export default RoomImageUpload;