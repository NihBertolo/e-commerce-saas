variable "cidr_block" {
  description = "The CIDR block for the VPC"
  type        = string
  default     = ""
}

variable "name" {
    description = "The name of the VPC"
    type        = string
    default     = "my-vpc"
}

variable "public_subnets" {
  description = "List of public subnet CIDR blocks"
  type        = list(string)
  default     = []
}

variable "availability_zones" {
    description = "List of availability zones for the subnets"
    type        = list(string)
    default     = []
}

variable "private_subnets" {
    description = "List of private subnet CIDR blocks"
    type        = list(string)
    default     = []
}