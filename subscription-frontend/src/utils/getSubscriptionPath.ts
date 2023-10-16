
const getSubscriptionPath = (pathPostfix: string) => {
    return "http://subscription-service:8080" + pathPostfix;
}

export default getSubscriptionPath;