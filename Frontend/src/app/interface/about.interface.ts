import { Image } from './image.interface';

export interface About {
  id: number;
  name: string;
  content: string;
  images: Image[];
}
