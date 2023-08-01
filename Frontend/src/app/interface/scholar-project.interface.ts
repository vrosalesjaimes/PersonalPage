import { Image } from './image.interface';
import { Reference } from './reference.interface';
import { Tag } from './tag.interface';
import { Author } from './author.interface';

export interface ScholarProject {
  id: number;
  title: string;
  description: string;
  initialImage: string;
  date: Date;
  repository: string;
  content: string;
  images: Image[];
  references: Reference[];
  tags: Tag[];
  authors: Author[];
}
