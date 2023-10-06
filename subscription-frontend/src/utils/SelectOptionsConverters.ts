
const getSelectOptions = (options: string[]) => {
    return options.map((option) => ({ value: option, label: option }));
}

export default getSelectOptions;