import React from 'react';


interface SvgRunImageProps {
    fill: string;
    size: number;
}

const SvgRunImage = ({
                         fill,
                         size
                     }: SvgRunImageProps) => (
    <svg width={size} height={size} viewBox="0 0 28 32" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path
            d="M25.5 11.6699C28.8333 13.5944 28.8333 18.4056 25.5 20.3301L7.5 30.7224C4.16667 32.6469 2.23054e-06 30.2413 2.39878e-06 26.3923L3.30731e-06 5.6077C3.47555e-06 1.75869 4.16667 -0.646934 7.5 1.27757L25.5 11.6699Z"
            fill={fill}/>
    </svg>
);

export default SvgRunImage;