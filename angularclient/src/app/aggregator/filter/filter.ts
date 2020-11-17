export class Filter {
  property: string;
  value: string;
  operator: string;

  constructor(property: string, value: string, operator: string) {
    this.operator = operator;
    this.property = property;
    this.value = value;
  }
}
