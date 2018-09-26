import history from '../history'
import { httpPostForm } from '../helpers/network'

export const LOG_IN = 'LOG_IN';
export const LOG_OUT = 'LOG_OUT';

export function loginAction(auth) {
    return dispatch => {
        const form = new FormData();
        form.append("j_username", auth.username);
        form.append("j_password", auth.password);

        const params = new URLSearchParams(form);
        httpPostForm("/j_security_login", params)
            .then(res => {
                console.log(res);
                dispatch({
                    type: LOG_IN,
                    data: {
                        userName: auth.username,
                        isAuth: true
                    }
                });
                history.push('/')
            })
            .catch(error => {
                console.warn("Auth failed with status: " +  error.response.status);
        })
    }
}

export function logoutAction() {
    return {
        type: LOG_OUT,
        data: {
            isAuth: false
        }
    }
}