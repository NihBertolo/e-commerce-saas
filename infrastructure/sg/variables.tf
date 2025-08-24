variable "name" {
    description = "The name of the security group"
    type        = string
    default     = "my-security-group"
}

variable "vpc_id" {
    description = "The ID of the VPC where the security group will be created"
    type        = string
    default     = ""
}

variable "ingress_rules" {
    description = "List of ingress rules for the security group"
    type = list(object({
        from_port   = number
        to_port     = number
        protocol    = string
        cidr_blocks = list(string)
        description = string
    }))
    default = []
}

variable "tags" {
    description = "A map of tags to assign to the security group"
    type        = map(string)
    default     = {}
}

variable "container_port" {
  description = "The port on which the container listens"
  type        = number
  default     = 80
}

variable "allowed_sgs" {
  description = "List of security group IDs allowed to access the ECS tasks"
  type        = list(string)
  default     = []
}