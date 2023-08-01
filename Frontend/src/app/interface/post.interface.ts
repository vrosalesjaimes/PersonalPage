import { Image } from './image.interface';
import { Tag } from './tag.interface';

export interface Post {
  id: number;
  title: string;
  description: string;
  initImage: string;
  date: Date;
  content: string;
  images: Image[];
  tags: Tag[];
}
