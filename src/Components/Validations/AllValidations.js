import * as yup from "yup";

export default class AllValidations {
    /*  -------- KULLANILMIYOR -----------  */ 
constructor() {
    //for register validation
const signUpFormValidation = yup.object.shape({
    name: yup.string().required(),
    surname: yup.string().required(),
    fatherName: yup.string().required(),
    motherName: yup.string().required(),
    hashPassword: yup.string().min(6).required(),
    tcNo: yup.string().required(),
    phone: yup.number().typeError("Tip hatası").min(8),
    birthPlace: yup.string().required(),
    birthDate: yup.string().required()
});



//this.loginFormValidation = loginFormValidation;
}
    
    
   
}


/*
export const signUpSchema = yup.object.shape({
    name: yup.string().required(),
    surname: yup.string().required(),
    fatherName: yup.string().required(),
    motherName: yup.string().required(),
    hashPassword: yup.string().min(6).required(),
    tcNo: yup.string().required(),
    phone: yup.number().typeError("Tip hatası").min(8),
    birthPlace: yup.string().required(),
    birthDate: yup.string().required()
});
*/
