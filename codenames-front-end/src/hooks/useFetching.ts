import {useState} from "react";

const useFetching = (callback: () => void): [(() => Promise<void>), boolean, string] => {
    const [isLoading, setIsLoading] = useState(false)
    const [error, setError] = useState('')

    const fetching = async () => {
        try {
            setIsLoading(true)
            await callback()
        } catch (e: any) {
            setError(e.message)
        } finally {
            setIsLoading(false)
        }
    }
    return [fetching, isLoading, error]
};

export default useFetching;