import React, {useEffect, useState} from "react"

/**
 * This component should show some information about a Star Wars character.
 * @returns {*}
 * @constructor
 */
const MyComponent = () => {
    const [name, setName] = useState("Unknown");
    const [error, setError] = useState(false);
    useEffect(() => {
        fetch('http://localhost:8080/api/v1/sw/character')
            .then(res => res.json())
            .then((data) => {
                setName(data.name);
                setError(false);
            })
            .catch((error) => {
                setError(true);
            });
    }, []);

    return <div>
        <div>
            Name: {name}
        </div>
        <div className={!error ? 'hidden' : ''}>
            Status: Can't fetch character information!
        </div>
    </div>
};

export default MyComponent
