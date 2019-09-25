import React, {useEffect, useState} from "react"

/**
 * This component should show some information about a Star Wars character.
 * @returns {*}
 * @constructor
 */
const MyComponent = () => {
    const [name, setName] = useState("Unknown");
    const [networkError, setNetworkError] = useState(false);
    useEffect(() => {
        fetch('http://localhost:8080/api/v1/sw/character')
            .then(res => res.json())
            .then((data) => {
                setName(data.name)
                setNetworkError(false)
            })
            .catch((error) => {
                setNetworkError(true)
            });
    }, []);

    return <div>
        <div>
            Name: {name}
        </div>
        <div className={!networkError ? 'hidden' : ''}>
            Status: Can't connect to the server!
        </div>
    </div>
};

export default MyComponent
