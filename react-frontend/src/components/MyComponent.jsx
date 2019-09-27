import React, {useEffect, useState} from "react"

/**
 * This component should show some information about a Star Wars character.
 * @returns {*}
 * @constructor
 */
const MyComponent = () => {
    const [name, setName] = useState("Unknown");
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(false);

    useEffect(() => {
        setLoading(true);
        fetch('http://localhost:8080/api/v1/sw/character')
            .then(res => res.json())
            .then((data) => {
                setLoading(false);
                if (data.integrationOk) {
                    setName(data.starWarsCharacter.name);
                    setError(false);
                } else {
                    setError(true);
                }
            })
            .catch((error) => {
                setLoading(false);
                setError(true);
            });
    }, []);

    return <div>
        <div className={loading || error ? 'hidden' : ''}>
            Name: {name}
        </div>
        <div className={!loading ? 'hidden' : ''}>
            Status: Loading...
        </div>
        <div className={!error ? 'hidden' : ''}>
            Error: Can't fetch character information!
        </div>
    </div>
};

export default MyComponent
