variable "cluster_name" {
    description = "The name of the ECS cluster"
    type        = string
    default     = "my-ecs-cluster"
}

variable "family" {
    description = "The family of the ECS task definition"
    type        = string
    default     = "my-ecs-task"
}

variable "cpu" {
    description = "The CPU units for the task"
    type        = string
    default     = "256"
}

variable "memory" {
    description = "The memory for the task"
    type        = string
    default     = "512"
}

variable "execution_role_arn" {
    description = "The ARN of the task execution role"
    type        = string
    default     = ""
}

variable "task_role_arn" {
    description = "The ARN of the task role"
    type        = string
    default     = ""
}

variable "container_name" {
    description = "The name of the container"
    type        = string
    default     = "my-container"
}

variable "container_image" {
    description = "The container image to use"
    type        = string
    default     = "nginx:latest"
}

variable "container_port" {
    description = "The port the container listens on"
    type        = number
    default     = 80
}

variable "environment" {
    description = "Environment variables for the container"
    type        = map(string)
    default     = {}
}

variable "secrets" {
    description = "Secrets for the container"
    type        = map(string)
    default     = {}
}

variable "service_name" {
    description = "The name of the ECS service"
    type        = string
    default     = "my-ecs-service"
}

variable "desired_count" {
    description = "The desired number of task instances"
    type        = number
    default     = 1
}

variable "subnets" {
    description = "List of subnet IDs for the ECS service"
    type        = list(string)
    default     = []
}

variable "security_groups" {
    description = "List of security group IDs for the ECS service"
    type        = list(string)
    default     = []
}

output "ecs_cluster_id" {
    description = "The ID of the ECS cluster"
    value       = aws_ecs_cluster.cluster.id
}

output "ecs_task_definition_arn" {
    description = "The ARN of the ECS task definition"
    value       = aws_ecs_task_definition.app.arn
}

output "ecs_service_id" {
    description = "The ID of the ECS service"
    value       = aws_ecs_service.app.id
}

