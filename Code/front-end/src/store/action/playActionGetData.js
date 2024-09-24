import * as apis from "../../apis"
import { 
    GETDATAUSER ,
    PRICEROOMSINGLE,
    PRICEROOMDOUBLE,
    PRICEROOMFAMILY,
    CHECKINDATE,
    CHECKOUTDATE,
    COUNTROOM,
    COUNTROOMDOUBLE,
    COUNTROOMFAMI,
    } from "./constants";

// Get apis User
export const getData = async(host) =>{
    try {
        const response = await apis.getUser(host)
        if(response.status === 200){
            return response
        }
        return{
            response: []
        }
    } catch (error) {
        return{
            response: []
        }
    }
}
export const GetDataUser = (resul) =>{
    return{
        type: GETDATAUSER,
        resul
    }
}// End apis user
// Manager
export const getDataManager = async(host) =>{
    try {
        const response = await apis.getManager(host)
        if(response.status === 200){
            return response
        }
        return{
            response: []
        }
    } catch (error) {
        return{
            response: []
        }
    }
}

// total price roomType
export const TotalPriceS = (priceS) => {
    return{
        type: PRICEROOMSINGLE,
        priceS
    }
} 
export const TotalPriceD = (priceD) => {
    return{
        type: PRICEROOMDOUBLE,
        priceD
    }
} 
export const TotalPriceF = (priceF) => {
    return{
        type: PRICEROOMFAMILY,
        priceF
    }
} 

// date checkin , checkout
export const CheckinDate = (date) => {
    return {
        type: CHECKINDATE,
        date
    }
}
export const CheckoutDate = (date) => {
    return {
        type:CHECKOUTDATE ,
        date
    }
}

// count Room  Single
export const CountNType = (countNtype) => {
    return {
        type: COUNTROOM,
        countNtype
    }
}

// count and type Double
export const CountNTypeDou = (countDou) => {
    return {
        type: COUNTROOMDOUBLE,
        countDou
    }
}

// count and type Family
export const CountNTypeFami = (countFami) => {
    return {
        type: COUNTROOMFAMI,
        countFami
    }
}