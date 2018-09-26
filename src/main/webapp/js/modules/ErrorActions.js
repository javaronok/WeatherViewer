export const APPEAR_ERROR = 'APPEAR_ERROR';
export const CLEAR_ERROR = 'CLEAR_ERROR';

export function appearError(error) {
    return {
        type: APPEAR_ERROR,
        text: error.responseText
    }
}

export function clearError() {
    return {
        type: CLEAR_ERROR
    }
}