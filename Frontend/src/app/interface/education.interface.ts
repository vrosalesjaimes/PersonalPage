import { Image } from "./image.interface";

export interface Education {
    id: number;
    title: string;
    institution: string;
    average: string;
    initialDate: Date;
    finalDate: Date;
    image: Image;
  }
  