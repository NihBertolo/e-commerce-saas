variable "name" {
    description = "The name prefix for Redis resources"
    type        = string
    default     = "my-redis"
}

variable "vpc_id" {
    description = "The ID of the VPC where Redis will be deployed"
    type        = string
    default     = ""
}

variable "private_subnets" {
    description = "List of private subnet IDs for the ElastiCache subnet group"
    type        = list(string)
    default     = []
}

variable "allowed_sgs" {
    description = "List of security group IDs allowed to access the Redis cluster"
    type        = list(string)
    default     = []
}

variable "node_type" {
    description = "The instance type for the Redis nodes"
    type        = string
    default     = "cache.t3.micro"
}

variable "parameter_group_name" {
    description = "The name of the parameter group to associate with the Redis cluster"
    type        = string
    default     = "default.redis7"
}

variable "num_cache_nodes" {
    description = "The number of cache nodes in the Redis cluster"
    type        = number
    default     = 1
}

variable "port" {
  description = "The port"
}

variable "tags" {
  description = "A map of tags to assign to the resources"
  type        = map(string)
  default     = {}
}