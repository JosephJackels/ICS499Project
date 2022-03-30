import { FormGroup } from '@angular/forms';

export function PasswordMatch(password: string, matchingPassword: string) {
    return (formGroup: FormGroup) => {
        const passwordControl = formGroup.controls[password];
        const matchingPasswordControl = formGroup.controls[matchingPassword];


        if (passwordControl.value !== matchingPasswordControl.value) {
            matchingPasswordControl.setErrors({passwordMatch: true})
        } else {
            matchingPasswordControl.setErrors(null);
        }
    }
}