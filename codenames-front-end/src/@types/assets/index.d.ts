

declare module 'react-transition-group'

declare module 'uuid'



declare module "*.svg" {
    const content: any;
    export default content;
}

declare module "\*.jpg" {
    const content: string;
    export default content;
}

declare module "\*.png" {
    const content: string;
    export default content;
}

declare module "\*.json" {
    const content: string;
    export default content;
}

interface Mapping{
    [key: string]: string;
}

declare module '\*.module.css'{
    const mapping: Mapping;
    export default mapping;
}

