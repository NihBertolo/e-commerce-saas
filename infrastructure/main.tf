terraform {
  required_version = ">= 1.0.0" # Ensure that the Terraform version is 1.0.0 or higher

  required_providers {
    aws = {
      source = "hashicorp/aws" # Specify the source of the AWS provider
      version = "~> 4.0"        # Use a version of the AWS provider that is compatible with version
    }
  }
}

provider "aws" {
  region = "us-east-1" # Set the AWS region to US East (N. Virginia)
}

module "vpc" {
  source = "./vpc" # Source the VPC module from the local 'vpc' directory

  name = "my-vpc" # Name tag for the VPC
  cidr_block = ""
  public_subnets = [] # List of CIDR blocks for public subnets}
  private_subnets = [] # List of CIDR blocks for private subnets
  availability_zones = [] # List of availability zones for the subnets
}

module "sg" {
  source = "./sg" # Source the security group module from the local 'sg' directory

  name = "my-security-group" # Name tag for the security groups
  vpc_id = module.vpc.vpc_id # Use the VPC ID from the VPC module
  container_port = 80 # Port on which the container listens
  allowed_sgs = [] # List of security group IDs allowed to access the ECS tasks
}

module "ecs" {
  source = "./ecs" # Source the ECS module from the local 'ecs' directory

  name = "my-ecs-cluster" # Name tag for the ECS cluster
  vpc_id = module.vpc.vpc_id # Use the VPC ID from the VPC module
  public_subnets = module.vpc.public_subnets # Use the public subnets from the VPC module
  private_subnets = module.vpc.private_subnets # Use the private subnets from the VPC module
  ecs_security_group_id = module.sg.ecs_sg_id # Use the ECS security group ID from the SG module
  rds_security_group_id = module.sg.rds_sg_id # Use the RDS security group ID from the SG module
}
module "rds" {
  source = "./rds" # Source the RDS module from the local 'rds' directory

  name = "my-rds-instance" # Name tag for the RDS instance
  vpc_id = module.vpc.vpc_id # Use the VPC ID from the VPC module
  private_subnets = module.vpc.private_subnets # Use the private subnets from the VPC module
  db_username = "admin" # Database username
  db_password = "password" # Database password (consider using a more secure method for production)
  db_name = "mydatabase" # Name of the initial database to create
  db_instance_class = "db.t3.micro" # Instance class for the RDS instance
  allocated_storage = 20 # Allocated storage in GB
  db_engine = "postgres" # Database engine (e.g., postgres, mysql)
  db_engine_version = "13.4" # Version of the database engine
  rds_security_group_id = module.sg.rds_sg_id # Use the RDS security group ID from the SG module
}
