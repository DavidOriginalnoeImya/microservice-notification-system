
const getResourcePath = (pathPostfix: string) => {
    return "http://registration-service:8080" + pathPostfix;
}

export default getResourcePath;