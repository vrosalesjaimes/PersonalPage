import { Tag } from "./tag.interface";

export interface Certification {
    id: number;
    title: string;
    description: string;
    initialImage: string;
    date: Date;
    link: string;
    content: string;
    tags: Tag[];
  }
  