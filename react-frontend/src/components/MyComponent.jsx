import React, {useEffect, useState} from "react"

/**
 * This component should show some information about a Star Wars character.
 * @returns {*}
 * @constructor
 */
const MyComponent = () => {
    const [name, setName] = useState("Luke");
    useEffect(() => {
        fetch('http://localhost:8080/api/v1/sw/character')
            .then(res => res.json())
            .then((data) => {
                setName(data.name)
            })
    }, []);

    return <div>
        Name: {name}
    </div>
};

export default MyComponent
