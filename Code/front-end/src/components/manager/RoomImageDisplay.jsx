import React, { useState, useEffect } from "react";
import axios from "axios";

const RoomImageDisplay = ({ imageId }) => {
  const [image, setImage] = useState(null);

  useEffect(() => {
    const fetchImage = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/room-images/${imageId}`, {
          responseType: "blob",
        });
        const imageUrl = URL.createObjectURL(new Blob([response.data]));
        setImage(imageUrl);
      } catch (error) {
        console.error("Error fetching image:", error);
      }
    };
    fetchImage();
  }, [imageId]);

  return (
    <div>
      {image ? <img src={image} alt="Room" /> : <p>No image available</p>}
    </div>
  );
};

export default RoomImageDisplay;
