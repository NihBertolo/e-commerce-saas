variable "name" {
    description = "The name of the RDS instance"
    type        = string
    default     = "my-rds-instance"
}

variable "db_name" {
    description = "The name of the database to create"
    type        = string
    default     = "mydatabase"
}

variable "username" {
    description = "The master username for the database"
    type        = string
    default     = "admin"
}

variable "password" {
    description = "The master password for the database"
    type        = string
    default     = "password"
    sensitive   = true
}

variable "instance_class" {
    description = "The instance class for the RDS instance"
    type        = string
    default     = "db.t3.micro"
}

variable "security_groups" {
    description = "List of security group IDs to associate with the RDS instance"
    type        = list(string)
    default     = []
}

variable "private_subnets" {
    description = "List of private subnet IDs for the RDS subnet group"
    type        = list(string)
    default     = []
}