// src/SearchBar.js
import React from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import "./SearchBar.css";

// Danh sách các thành phố
const cities = [
  { value: "hanoi", label: "Hà Nội" },
  { value: "hochiminh", label: "TP Hồ Chí Minh" },
  { value: "danang", label: "Đà Nẵng" },
  { value: "halong", label: "Hạ Long" },
];

const SearchBar = () => {
  return (
    <Formik
      initialValues={{
        location: "",
        checkInDate: "",
        checkOutDate: "",
        guests: 1,
      }}
      onSubmit={(values) => {
        console.log("Dữ liệu tìm kiếm:", values);
      }}
    >
      {({ setFieldValue, values }) => (
        <Form className="search-bar">
          <div className="form-group">
            <label htmlFor="location">Địa điểm</label>
            <Field as="select" id="location" name="location">
              <option value="" label="" />
              {cities.map((city) => (
                <option key={city.value} value={city.value}>
                  {city.label}
                </option>
              ))}
            </Field>
          </div>

          <div className="form-group">
            <label htmlFor="check-in-date">Ngày nhận phòng</label>
            <Field
              id="check-in-date"
              name="checkInDate"
              type="date"
              placeholder="Chọn ngày nhận phòng"
            />
          </div>

          <div className="form-group">
            <label htmlFor="check-out-date">Ngày trả phòng</label>
            <Field
              id="check-out-date"
              name="checkOutDate"
              type="date"
              placeholder="Chọn ngày trả phòng"
              min={values.checkInDate ? values.checkInDate : undefined}
            />
          </div>

          <div className="form-group">
            <label htmlFor="guests">Số lượng khách</label>
            <Field id="guests" name="guests" type="number" min="1" />
            <ErrorMessage
              name="guests"
              component="div"
              className="error-message"
            />
          </div>

          <button type="submit">Tìm kiếm</button>
        </Form>
      )}
    </Formik>
  );
};

export default SearchBar;
