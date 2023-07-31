import { Image } from './image';

export interface About {
  id: number;
  name: string;
  content: string;
  images: Image[];
}
